package nthPower;

import java.math.BigDecimal;

import static nthRoot.nthRoot.nthRoot;

/*
	NOTE: Always compile with '-d .'
		And always run with <package-name>.<class-name> format
*/
public class nthPower{
	public static void main( String... parameterList ){
		String value = parameterList[ 0 ];

		String exponent = parameterList[ 1 ];

		BigDecimal result = nthPower( value, exponent );

		System.out.print( result.toString( ) );
	}

	public static final BigDecimal nthPower( String value, String exponent ){
		BigDecimal baseValue = new BigDecimal( value );
		BigDecimal baseExponent = new BigDecimal( exponent );

		String[ ] baseExponentPartList = baseExponent.toString( ).split( "\\." );
		if( baseExponentPartList.length > 1 ){

			String exponentDecimal = baseExponentPartList[ 1 ];
			String partialBaseExponent = baseExponentPartList[ 0 ];

			int exponentDecimalLength = exponentDecimal.length( );
			String zeroSequence = new String( new char[ exponentDecimalLength ] ).replace( "\0", "0" );
			String exponentFraction = "1" + zeroSequence;

			BigDecimal phaseA = nthPower( value, partialBaseExponent );

			BigDecimal phaseB = nthPower( value, exponentDecimal );

			BigDecimal phaseC = nthRoot( phaseB, exponentFraction );

			return phaseA.multiply( phaseC );

		}else{

			if( baseExponent.remainder( new BigDecimal( "2" ) ).compareTo( BigDecimal.ZERO ) != "0" ){
				return nthPower( value, baseExponent.subtract( BigDecimal.ONE ).toString( ) ).multiply( baseValue );

			}else{
				BigDecimal result = baseValue;
				BigDecimal partialBaseExponent = new BigDecimal( "2" );

				BigDecimal procedureCount = baseExponent.divide( partialBaseExponent );
				BigDecimal count = BigDecimal.ONE;

				do{
					result = result.pow( partialBaseExponent.intValue( ) );

					count = count.add( BigDecimal.ONE );

				}while( count.compareTo( procedureCount ) != 0 );

				return result;    
			}
		}
	}
}