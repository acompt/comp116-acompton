Andrea Compton
9/14/2014
Security Assignment 1

***************************************************
set1.pcap

1. Number of Packets: 1503
2. Protocol used to transfer files from PC to server: FTP
3. Why protocol used to transfer files is insecure:
		FTP does not encript the packets so usernames and passwords can be read easily.
4. Secure alternative to protocol used: SSH
5. IP address of the server: 10.245.145.124
6. Username and password used to access the server:
		user: ihackpineapples
		pass: rockyou1
7. Number of files transfered from PC to server: 4
8. Names of the files transfered from PC to server:
		smash.txt
		BvzjaN-IQAA3XG7.jpg
		BvgT9p2IQAEEoHu.jpg
		BjN-O1hCAAAZbiq.jpg
9. Extract files

set2.pcap

10. How many packets in this set: 77882
11. How many plaintext username-password pairs in this set: 2
12. How I found username-password pairs:
		sudo ettercap –T –r set0.pcap | grep “PASS:”
13. For each pair: protocol, server IP, domain name, port number
		1.  Protocol: pop
			IP: 75.126.75.131
			Name: stamps.com
			Port: 110
		2.  Protocol: http
			IP: 75.127.96.187
			Name: defcom-wireless-village.com
			Port: 80

14. How many pairs are legitimate:
		2
15. How did I verify successful pairs:
		Search IP address in wireshark and follow tcp stream to see if login was successful.
16. Advice to users:
		Use a secure protocol.