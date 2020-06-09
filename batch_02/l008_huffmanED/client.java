public class client{
    public static void main(String[] args){
        HuffmanED hed=new HuffmanED("asdgvajdvmnasvcamnsvcasdaskmndbckasjbdcmanbscmsanbcmsnbcmsnbcmsnbcdmdsncmsbcmsnbcskgymnszdcvmzcvn");
       
        String str=args[0];
       
        String encode=hed.encodeString(str);
        String decode=hed.decodeString(encode);

        System.out.println(encode+ " -> " + decode);

    }
}