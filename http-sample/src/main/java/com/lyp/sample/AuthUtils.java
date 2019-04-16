package com.lyp.sample;

/**
 * Created by arthur on 16/7/13.
 */
public class AuthUtils
{
	public static boolean checkSignature(String secret, Long timestamp, Integer nonce, String signature)
	{
		StringBuilder rawString = new StringBuilder();
		rawString.append(secret);
		rawString.append(nonce);
		rawString.append(timestamp);
		String mySignature = CryptoUtils.sha1(rawString.toString());

		if (!mySignature.equals(signature))
		{
			return false;
		}
		return true;
	}

	public static String generateSignature(String secret, Long timestamp, Integer nonce)
	{
		StringBuilder rawString = new StringBuilder();
		rawString.append(secret);
		rawString.append(nonce);
		rawString.append(timestamp);
		System.out.println(rawString.toString());
		String mySignature = CryptoUtils.sha1(rawString.toString());
		return mySignature.toString();
	}

	public static boolean isValidSignature(String secret, Long timestamp, Integer nonce, String signature)
	{
		StringBuilder rawString = new StringBuilder();
		rawString.append(secret);
		rawString.append(nonce);
		rawString.append(timestamp);
		String mySignature = CryptoUtils.sha1(rawString.toString());

		if (!mySignature.equals(signature))
		{
			return false;
		}
		return true;
	}

	public static void main(String[] args){
		String secret = "pq0194mxoqfh48L362G6R09T737E273X";
		int nonce = 603663184;
		long timestamp = 60366318;
        String signature = "7796ca12eaf72e80a04caba359e9cef355de275f\n";
        System.out.println(generateSignature(secret, timestamp, nonce));
		System.out.println(isValidSignature(secret, timestamp, nonce, signature));
	}

}
