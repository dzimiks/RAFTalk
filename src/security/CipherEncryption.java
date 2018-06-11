package security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Omogucava enkripciju i dekripciju poruka.
 * 
 * @author dzimiks
 */
public class CipherEncryption {

	// SECRET KEY
	private static byte[] key = {0x74, 0x68, 0x69, 0x73, 
								 0x49, 0x73, 0x41, 0x53, 
								 0x65, 0x63, 0x72, 0x65, 
								 0x74, 0x4b, 0x65, 0x79};
	/**
	 * Enkriptuje zadatu poruku.
	 * 
	 * @param stringToEncrypt - poruka koja ce biti enkriptovana.
	 * @return enkriptovana poruka
	 */
	public static String encrypt(String stringToEncrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			final String encryptedString = Base64.encodeBase64String(cipher.doFinal(stringToEncrypt.getBytes()));
			return encryptedString;
		}
		catch (Exception e) {
//			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Dekriptuje zadatu poruku.
	 * 
	 * @param stringToDecrypt - poruka koja ce biti dekriptovana.
	 * @return dekriptovana poruka
	 */
	public static String decrypt(String stringToDecrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(stringToDecrypt)));
            return decryptedString;
		}
		catch (Exception e) {
//			e.printStackTrace();
		}
		
		return null;
	}

	// Test program za proveru Cipher-a
	public static void main(String[] args) {
		
		try {
			String message = "Test 2";
			String encrypted = encrypt(message);
			String decrypted = decrypt(encrypted);

			System.out.println("Encrypted: " + encrypted);
			System.out.println("Decrypted: " + decrypted);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}