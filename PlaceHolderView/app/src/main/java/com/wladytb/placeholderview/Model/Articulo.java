package com.wladytb.placeholderview.Model;

public class Articulo {
    private String date_published,title,doi,section,publication_id,abstractt,submission_id,section_id,
            seq,galeys;

    public Articulo() {
    }

    public Articulo(String date_published, String title, String doi, String section, String publication_id, String abstractt, String submission_id, String section_id, String seq, String galeys) {
        this.date_published = date_published;
        this.title = title;
        this.doi = doi;
        this.section = section;
        this.publication_id = publication_id;
        this.abstractt = abstractt;
        this.submission_id = submission_id;
        this.section_id = section_id;
        this.seq = seq;
        this.galeys = galeys;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(String publication_id) {
        this.publication_id = publication_id;
    }

    public String getAbstractt() {
        return abstractt;
    }

    public void setAbstractt(String abstractt) {
        this.abstractt = abstractt;
    }

    public String getSubmission_id() {
        return submission_id;
    }

    public void setSubmission_id(String submission_id) {
        this.submission_id = submission_id;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getGaleys() {
        return galeys;
    }

    public void setGaleys(String galeys) {
        this.galeys = galeys;
    }
}
