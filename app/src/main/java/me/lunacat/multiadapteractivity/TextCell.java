package me.lunacat.multiadapteractivity;

public class TextCell implements ITableCell{
    private String text;
    private String wikiUrl;
    public TextCell(String text, String wikiUrl) {
        this.text    = text;
        this.wikiUrl = wikiUrl;
    }

    public String getText() {
        return this.text;
    }

    public String getWikiUrl() {
        return this.wikiUrl;
    }
}
