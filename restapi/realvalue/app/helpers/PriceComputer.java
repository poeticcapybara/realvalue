package helpers;

public class PriceComputer {

	private static double weights[]={8483.33776,2858.76023};
	private static double intercept =  84448.20219166;
	
	private PriceComputer(){
		//
	}
	
	public static double computePrice(double surfaceLiving, double numberRooms){
		return weights[0]*surfaceLiving+weights[1]*numberRooms+intercept;
	}
}
