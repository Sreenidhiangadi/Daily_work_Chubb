import java.util.*;
import java.io.*;
 class Fileops {

	public static void main(String[] args) throws FileNotFoundException {
		String filename="fileop.txt";
		BufferedReader reader=new BufferedReader(new FileReader(filename));
		String line;
		try {
			double totalAmountHDFC = 0;
		while((line=reader.readLine())!=null)
		{
			  String[] data = line.split(",");

              String senderName = data[0];
              String senderIFSC = data[3];
              double senderBalance = Double.parseDouble(data[4]);
              double transferAmount = Double.parseDouble(data[5]);
              String receiverName = data[7];
              String receiverIFSC = data[10];
              if (transferAmount > 0 && senderBalance >= transferAmount) {
                  System.out.println("Transfer Approved: " + senderName + " → " + receiverName +
                          " | Amount: " + transferAmount);

                  // Check if sender bank is HDFC (starts with HDFC)
                  if (senderIFSC.startsWith("HDFC")) {
                      totalAmountHDFC += transferAmount;
                  }

              } else {
                  System.out.println(" Transfer Rejected: " + senderName + 
                          " | Amount: " + transferAmount + " | Balance: " + senderBalance);
              }
              
		}
		 System.out.println("\n Total amount paid by HDFC Bank: " + totalAmountHDFC);

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
