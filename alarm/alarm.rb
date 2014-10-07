#!/usr/bin/env ruby

require 'packetfu'
require 'optparse'

$count = 0

def checkNull(pkt)
  found = false
  if pkt.tcp_flags.to_i() == 0
    found = true
  end
  found
end

def checkXmas(pkt)
  found = false
  if (pkt.tcp_flags.urg == 1 && pkt.tcp_flags.psh == 1 && pkt.tcp_flags.fin == 1 \
     && pkt.tcp_flags.ack == 0 && pkt.tcp_flags.rst == 0 && pkt.tcp_flags.syn == 0)
    found = true
  end
  found
end

def checkCredit(pkt)
  found = false
  if (/4\d{3}(\s|-)?\d{4}(\s|-)?\d{4}(\s|-)?\d{4}/ =~ pkt.payload \
      || /5\d{3}(\s|-)?\d{4}(\s|-)?\d{4}(\s|-)?\d{4}/ =~ pkt.payload \
      || /6011(\s|-)?\d{4}(\s|-)?\d{4}(\s|-)?\d{4}/ =~ pkt.payload \
      || /3\d{3}(\s|-)?\d{6}(\s|-)?\d{5}/ =~ pkt.payload)
    found = true
  end
  found
end

def printError(type, ip, protocol, payload)
  puts "#{$count}. ALERT: #{type} is detected from #{ip} 
		(#{protocol}) (#{payload})!"
end

def getPayload(pkt)
  payload = pkt.payload().each_byte.map { |b| sprintf("0x%02X ",b) }.join
end

def checkPacket(pkt)
  if checkNull(pkt)
    payload = getPayload(pkt)
    $count += 1
    printError("NULL scan", pkt.ip_saddr, pkt.proto(), payload)
  end
  if checkXmas(pkt) 
    payload = getPayload(pkt)
    $count += 1
    printError("Xmas scan", pkt.ip_saddr, pkt.proto(), payload)
  end
  if checkCredit(pkt) 
    payload = getPayload(pkt)
    $count += 1
    printError("Credit card leaked in the clear", pkt.ip_saddr, 
		"HTTP", payload)
  end
end

def checkLog(line)
  ip = line.scan(/[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}/)
  payload = line.scan(/".*/)
  if /Nmap/ =~ line
    $count += 1 
    printError("Nmap scan", ip.join, "HTTP", payload.join)
  end
  if /HTTP.* 404/ =~ line
    $count += 1
    printError("HTTP Error", ip.join, "HTTP", payload.join)
  end
  if /\\x.*\\x.*\\x/ =~ line
    $count += 1
    printError("Shell code", ip.join, "HTTP", payload.join)
  end
end

options = {:logfile => nil}
parser = OptionParser.new do |o|
  o.on('-r', '--r logfile', 'Logfile') do |logfile|
    options[:logfile] = logfile;
  end
end

parser.parse!

if options[:logfile]
  File.open(options[:logfile], "r") do |file_handle|
    file_handle.each_line do |line|
        if !line.nil?
          checkLog(line)
        end
    end
  end
else    
  captured = PacketFu::Capture.new(:start=>true, :iface=>'eth0', :promisc=>true)
  captured.stream.each do |p|
    pkt = PacketFu::Packet.parse(p)
    if pkt.is_tcp?
      checkPacket(pkt)
    end
  end
  stream.show_live()
end


