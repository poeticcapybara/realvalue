package helpers;

public class AdIdExtractor {
	
	private AdIdExtractor(){
		//
	}

	private static String[] buyWords = {"buy", "kaufen", "acheter","acquistare"};
	private static String[] rentWords = {"rent","mieten","louer","affittare"};
	
	public static long extractAdId(String adURL){
		if ((adURL==null) || (adURL.isEmpty())) return -1;
		//Extract the advertisementId
		//Check if the URL contains the word buy
		int adIdStartPos=-1;
		for(int i=0;i<buyWords.length;i++){
			adIdStartPos = adURL.indexOf(buyWords[i]);
			if (adIdStartPos!=-1) {
				adIdStartPos += buyWords[i].length()+1;
				break;
				}
		}
				
		//Check if the URL contains the word rent if rent has not been found
		if (adIdStartPos==-1){
			for(int i=0;i<rentWords.length;i++){
				adIdStartPos = adURL.indexOf(rentWords[i]);
				if (adIdStartPos!=-1) {
					adIdStartPos += rentWords[i].length()+1;
					break;
				}
			}
		}
				
		if (adIdStartPos==-1) return -1;
		String extendedAdId = adURL.substring(adIdStartPos);
		//Check if a ? is found in extendedAdId
		String adIdStr = extendedAdId;
		int quotePos = extendedAdId.indexOf('?');
		long addId = -1;
		if (quotePos!=-1) adIdStr = extendedAdId.substring(0, quotePos);
		//If the string is not a proper number set the resulting number to -1
		try{
			addId = Long.parseLong(adIdStr);
		}
		catch(NumberFormatException e)
		{
			addId = -1;
		}
		return addId;
	}
}
