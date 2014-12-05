Comp116
Assignment #5
Aansh Kapadia & Andrea Compton


PART A: IMAGES
After downloading all three image files, we determined the b.jpg was different
 than the others by using “diff”. Then, in order to crack the passphrase in 
 order to use steghide, we created a simple script (bruteforce.sh) to 
 brute-force the passphrase against a john-the-ripper’s wordlist. After 
 running the script, we found that the passphrase was “disney”. Entering the 
 correct passphrase when using steghide on b.jpg resulted in an executable 
 file named runme. Then in order to further analyze the file, we ran strings 
 on runme. This led us to run runme with the argument 
 “blinky_the_wonder_chimp,” which resulted in a message directing us to email you.


Step 1: download the images
    Downloaded all images: a.jpg, b.jpg, c.jpg.
        -a.jpg and c.jpg are identical, while b.jpg is the odd one out.
            (Figured out with “diff”)
            $ diff a.jpg b.jpg
        -Binary files a.jpg and b.jpg differ
            $ diff a.jpg c.jpg
            $ diff b.jpg c.jpg
        -Binary files b.jpg and c.jpg differ        


Step 2: crack the b.jpg passphrase
    Bash Script (to find the passphrase)
        file=`cat /usr/share/john/password.lst`
            for password in $file ;
            do
            if steghide extract -sf ~/Desktop/a.jpg -xf runme -p $password
                printf "$password"
            fi
        done
    passphrase for b.jpg: “disney”


Step 3: find the strings in the file
    After extracting the executable file and running “strings runme”, 
    the hidden information we found was:
        
    /lib64/ld-linux-x86-64.so.2
    __gmon_start__
    libc.so.6
    strcpy
    puts
    printf
    strcmp
    __libc_start_main
    GLIBC_2.2.5
    UH-`
    UH-`
    fffff.
    l$ L
    t$(L
    |$0H
    blinky_the_wonder_chimp
    Perhaps use your first name as an argument.
    Please send me an email with the subject: I believe that I will win!
    %s, you are doing a heckuvajob up to this point!
    ;*3$"


    chmod 777 runme
    ./runme blinky_the_wonder_chimp


PART B: Analyzing the Disk


1. What is/are the disk format(s) of the disk on the suspect's computing device?
   2 partitions: 
      1. C:// File System - FAT16 (sectors 1-125,000)
      2. /2/ File System - Ext (sectors 125001-6143999)


2. Is there a phone carrier involved?
   There is no phone carrier involved. This is most likely a Raspberry Pi device 
   since it contains software like “Broadcom,” which is “software that may only be 
   used of developing for, running or using a Raspberry Pi device.”


3. What operating system, including version number, is being used? 
   Please elaborate how you determined this information.
        Found in /2/etc/os-release
        1. Source OS: Linux
        2. Version: Kali GNU/Linux 1.0


4. What applications are installed on the disk? Please elaborate how you determined this information.
    Looked in directories: “/usr/share/applications” and other folders within “/2/etc”

    Applications:
        The disk had many the applications that come with Kali, such as..
        1. Iceweasel browser
        2. Wireshark
        3. Image Magick
        4. John
        5. SQLMap
        
5. Is there a root password? If so, what is it?
    Yes. Password: princess


6. Are there any additional user accounts on the system? If so, what are their passwords?
    go to /2/etc/shadow and /2/etc/passwd and copy files. 
    Unshadow with john the ripper then run against wordlists.
        users:                         passwords:
            alejandro                       pokerface
            judas                           00000000
            stefani                         iloveyou


7. List some of the incriminating evidence that you found. 
   Please elaborate where and how you uncovered the evidence.
        1. we_arnt_dead_yet.pdf naked security pdf: For Nearly 20 years the launch 
            code for US nuclear missiles was 00000000 
            found  in /2/home/judas/ then extracted and opened
        2. found 17 pictures of lady gaga and a ringtone of bad romance 
            in /2/home/alejandro. Extracted and opened.
        3. video of the nyu performance and schedule of her upcoming shows found in
            /2/home/stefani. Extracted and opened.
        4. All the user accounts and passwords are names of her songs or about her songs.
            Used john the ripper to brute force the passwords of each user.
    5. Encrypted files on the system
    6. Lots of deleted files
                                
        
8. Did the suspect move or try to delete any files before his arrest? 
   Please list the name(s) of the file(s) and any indications of their contents that you can find.
        Deleted 3 pictures: a15.jpg, a16.jpg, a17.jpg. 
            Probably pictures of Lady Gaga as they were in the same folder and named 
            similarly as the other pictures of her.
        If you search for all deleted files, there are tons of other files that were 
            deleted which may have been because the user tried to wipe the system.


9. Did the suspect save pictures of the celebrity? 
   If so, how many pictures of the celebrity did you find? (including any deleted images)
        Yes, there were 17 pictures


10. Are there any encrypted files? If so, list the contents in the encrypted file and provide 
    a brief description of how you decrypted the file.
        All zip files:
            1. rename using cp for example: cp lockbox.txt lockbox.zip
            2. unzip: unzip lockbox.zip
            3. crack the password using fcrackzip: fcrackzip -D -p wordlist lockbox.zip
        1. lockbox.txt is actually edge.pm4, a video of Lady Gaga performing with Howard Stern
                lockbox password = gaga
        Found when searched for .ncf:
        2. vol3-2.lib.firmware.vxge.X3fw-pxe.ncf
        3. vol3-2.lib.firmware.vxge.X3fw.ncf


11. Do the suspect want to go see this celebrity? 
    If so, note the date(s) and location(s) where the suspect want see to the celebrity.
        found in: /2/home/stefani/sched.txt
            12/31/2014: The Chelsea at the Cosmopolitan of Las Vegas Las Vegas, NV 9:00 p.m. PST
            2/8/2015: Wiltern Theatre, Los Angeles, CA, 9:30 p.m. PST
            5/30/2015: Hollywood Bowl, Hollywood, CA, 7:30 p.m. PDT
            There was also a video of her NYU performance in 2005.


12. Who is the celebrity that the suspect has been stalking?
        Lady Gaga

        