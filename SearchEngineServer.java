import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

class SearchEngine implements URLHandler {
    ArrayList<String> stringList = new ArrayList<>();
        public String handleRequest(URI url) {
            if(url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    stringList.add(parameters[1]);
                }
                return "List" + Arrays.toString(stringList.toArray());
            }
            else {
                ArrayList<String> returnList = new ArrayList<>();
                returnList.clear();
                if(url.getPath().contains("/search")) {
                    String[] parameters = url.getQuery().split("=");
                    if (parameters[0].equals("s")) {
                        for(int i = 0; i < stringList.size(); i ++) {
                            if(stringList.get(i).contains(parameters[1])) {
                                returnList.add(stringList.get(i));
                            }
                        }
                        return "Strings found:" + Arrays.toString(returnList.toArray());
                    }
                }
                return "String not found";
        }
    }
}

class SearchEngineServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchEngine());
    }
}