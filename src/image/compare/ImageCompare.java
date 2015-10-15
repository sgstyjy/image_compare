package image.compare;

import java.io.IOException;
import java.util.Date;

import jxl.JXLException;
import jxl.write.biff.JxlWriteException;

public class ImageCompare {

	public static void main(String[] args) throws IOException, JxlWriteException, JXLException {
		
		/*
		 * @para file1,    file1 is the main file, that is, the image will be migrated
		 * @para file2    
		 * @para blocksize
		 */
		Constant.file1=args[0];
		Constant.file2=args[1];
		int x = Integer.parseInt(args[2]);
		Constant.blocksize=x*1024;
		Constant.hashtable1=Constant.file1.split(".")[0]+".xsl";   //generate the hashtable name for file1
		Constant.hashtable1=Constant.file2.split(".")[0]+".xsl";   //geerate the hashtable name for file2
		Constant.compareresult=Constant.file1.split(".")[0]+"_"+Constant.file2.split(".")[0]+"_"+x+".xsl";    //generate the compare result table name
		//the start time
		Date date = new Date();
		Long starttime = date.getTime();
		//System.out.println("The start time is: "+starttime);
		
		//generate the hashtables
		Long starttime_hash1 = date.getTime();
		GenerateHash hashgenerater = new GenerateHash();  
		Constant.totalblocks1 = hashgenerater.generater(Constant.file1,Constant.hashtable1);
		System.out.println("The  total block numbers of the first image  are: "+Constant.totalblocks1);
		Long endtime_hash1 = date.getTime();
		Long hashtime1=endtime_hash1-starttime_hash1;
		System.out.println("The hash time of first image is:"+hashtime1);
		
		Long starttime_hash2 = date.getTime();
		Constant.totalblocks2  = hashgenerater.generater(Constant.file2,Constant.hashtable2);
		System.out.println("The  total block numbers of the second image  are: "+Constant.totalblocks2);
		Long endtime_hash2 = date.getTime();
		Long hashtime2=endtime_hash2-starttime_hash2;
		System.out.println("The hash time of second image is:"+hashtime2);
				
		//compare hashtables
		Long starttime_compare = date.getTime();
		CompareHash comparer = new CompareHash();
		comparer.fastCompareHash(Constant.hashtable1, Constant.hashtable2);
		Long endtime_compare = date.getTime();
		Long comparetime=endtime_compare-starttime_compare;
		System.out.println("The hash time of second image is:"+comparetime);
		
		//the end time
		Long endtime = date.getTime();
		Long temp = endtime-starttime;
		Long duration;
		if(hashtime1>hashtime2)
			   duration = temp-hashtime2;
		else
			   duration = temp-hashtime1;
		System.out.println("The total time is:"+duration);
	}

}
