package xiphoray.waimaizhushou;


public class Data {

    private String new_title;
    private String new_content;

    Data(String new_title, String new_content) {
        this.new_title = new_title;
        this.new_content = new_content;
    }

    String getNew_title() {
        return new_title;
    }

    String getNew_content() {
        return new_content;
    }

}
