package ViewModel;

import Command.*;
import Repo.ArtWorkRepository;

import javax.swing.*;

public class ArtWorkListViewModel {

    private ArtWorkRepository artWorkRepository;
    private String title;
    private String author;
    private Integer year;
    private String type;
    private JList<String> listArts;
    private DefaultListModel<String> listModelArts;
    public CSearchArtWorkByVisitor cSearchArtWorkByVisitor;
    public CListArtWorksForVisitor cListArtWorksForVisitor;
    public CToFilterTitleForVisitator cToFilterTitleForVisitator;
    public CToFilterAuthorForVisitor cToFilterAuthorForVisitor;
    public CToFilterYearForVisitor cToFilterYearForVisitor;
    public CToFilterTypeForVisitor cToFilterTypeForVisitor;


    public ArtWorkListViewModel() {
        this.artWorkRepository = new ArtWorkRepository();
        this.cSearchArtWorkByVisitor = new CSearchArtWorkByVisitor(artWorkRepository, this);
        this.cListArtWorksForVisitor = new CListArtWorksForVisitor(artWorkRepository, this);
        this.cToFilterTitleForVisitator = new CToFilterTitleForVisitator(artWorkRepository, this);
        this.cToFilterAuthorForVisitor = new CToFilterAuthorForVisitor(artWorkRepository, this);
        this.cToFilterYearForVisitor = new CToFilterYearForVisitor(artWorkRepository, this);
        this.cToFilterTypeForVisitor = new CToFilterTypeForVisitor(artWorkRepository, this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JList<String> getListArts() {
        return listArts;
    }

    public void setListArts(JList<String> listArts) {
        this.listArts = listArts;
    }

    public DefaultListModel<String> getListModelArts() {
        return listModelArts;
    }

    public void setListModelArts(DefaultListModel<String> listModelArts) {
        this.listModelArts = listModelArts;
    }

    public void SearchArtWorkByVisitor()
    {
        cSearchArtWorkByVisitor.execute();
    }

    public void ListArtWorksForVisitor()
    {
        cListArtWorksForVisitor.execute();
    }

    public void ToFilterTitleForVisitor()
    {
        cToFilterTitleForVisitator.execute();
    }

    public void ToFilterAuthorForVisitor()
    {
        cToFilterAuthorForVisitor.execute();
    }

    public void ToFilterYearForVisitor()
    {
        cToFilterYearForVisitor.execute();
    }

    public void ToFilterTypeForVisitor()
    {
        cToFilterTypeForVisitor.execute();
    }

}
