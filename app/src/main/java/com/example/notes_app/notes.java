package com.example.notes_app;

public class notes {
    private String Hedder;
    private String Details;

    public notes(String hedder, String details) {
        Hedder = hedder;
        Details = details;
    }

    public String getHedder() {
        return Hedder;
    }
    public void setHedder(String hedder) {
        Hedder = hedder;
    }


    public String getDetails() {
        return Details;
    }



    public void setDetails(String details) {
        Details = details;
    }
}
