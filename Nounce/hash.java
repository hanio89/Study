import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

class BitcoinMiningDemo {
    private static String bytesToHexString(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == i)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodehash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < encodehash.length; i++) {
                sb.append(Integer.toString((encodehash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
        }
        return input;
    }

    public static void main(String args[]) {
        int difficulty = Integer.valueOf(args[0]);
        String target = "";
        for (int i = 0; i < difficulty; i++) {
            target = target + "0";
        }
        long start = System.currentTimeMillis();

        String hash = "";
        String timestamp = Long.toString(start);
        String data = "Bod transfer to Alice $200";
        int nonce = 0;

        while (!hash.startsWith(target)) {
            nonce = nonce + 1;
            hash = sha256(timestamp + data + nonce);
            System.out.println(nonce + "\t" + hash);
        }

        long stop = System.currentTimeMillis();
        System.out.println("=================\nDifficult Level: " + difficulty + " -Time elapsed: " + (stop - start)
                + "ms - Counter: " + nonce);
    }
}