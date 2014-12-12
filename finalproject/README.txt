ASSURED DELETION IN THE CLOUD
By: Andrea Compton
Mentor: Ming Chow
Tufts University

CONTENTS

1. paper.ptf - the paper in PDF format

2. MapDisplay.java - addition to existing Amazon services. 
    Parses log file to obtain the number of actions per location which are then then placed on a map.
    If the number is clicked, a textbox appears stating the users at that location.

    The first click works well, however, later clicks seem to take a long time.

3. ListPanel.java - Displays list of users for each location

4. LogExample.java - parses the given test.txt file

5. test.txt - example file containing users and locations. Can easily be obtained
            through Amazon's service:
            //taken from: http://docs.aws.amazon.com/awscloudtrail/latest/userguide/log_examples.html

                LogParser parser = new LogParser();
                InputStream stream = null;
         
                parser.parse(stream, new ILogParserCallback() {
                  public void logEntryParsed(ILogEntry entry) {
                    String ip = new String(entry.getFieldValue("c-ip"));
                    String file = new String(entry.getFieldValue("cs-uri-stem"));
                    String bytes = new String(entry.getFieldValue("sc-bytes"));
                 
                    System.out.println("Client from " + ip + " downloaded " + file
                                            + " using " + bytes + " bytes.");
                  }
                });

6. Aws_region_map.png - map showing all of Amazon's supported regions

