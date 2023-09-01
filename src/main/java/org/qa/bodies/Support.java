package org.qa.bodies;

public class Support {

    private String url;
    private String text;

    public Support() { }

    public Support(String url, String text) {

        this.url = url;
        this.text = text;
    }

    public String getUrl() {

        return url;
    }

    public String getText() {

        return text;
    }

    @Override
    public boolean equals(Object object) {

        if(this == object) {

            return true;
        }

        if(!(object instanceof Support support)) {

            return false;
        }

        return this.url.equals(support.url) && this.text.equals(support.text);
    }
}
