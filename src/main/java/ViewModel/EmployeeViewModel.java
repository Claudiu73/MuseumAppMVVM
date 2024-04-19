package ViewModel;

import Command.*;
import Repo.ArtWorkRepository;

import javax.swing.*;


public class EmployeeViewModel {
    private ArtWorkRepository artWorkRepository;
    private String title;
    private String author;
    private Integer year;
    private String type;
    public CAddArtWork cAddArtWork;
    public CDeleteArtWork cDeleteArtWork;
    public CUpdateArtWork cUpdateArtWork;
    public CSearchArtWork cSearchArtWork;
    public CGenerateCSVFile cGenerateCSVFile;
    public  CGenerateJSONFile cGenerateJSONFile;
    public  CGenerateXMLFile cGenerateXMLFile;
    public CGenerateDOCFile cGenerateDOCFile;
    public CListArtWorks cListArtWorks;
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> list;

    public EmployeeViewModel() {
        this.artWorkRepository = new ArtWorkRepository();
        this.cAddArtWork = new CAddArtWork(artWorkRepository, this);
        this.cDeleteArtWork = new CDeleteArtWork(artWorkRepository, this);
        this.cUpdateArtWork = new CUpdateArtWork(artWorkRepository, this);
        this.cSearchArtWork = new CSearchArtWork(artWorkRepository, this);
        this.cGenerateCSVFile = new CGenerateCSVFile(artWorkRepository, this);
        this.cGenerateJSONFile = new CGenerateJSONFile(artWorkRepository, this);
        this.cGenerateXMLFile = new CGenerateXMLFile(artWorkRepository, this);
        this.cGenerateDOCFile = new CGenerateDOCFile(artWorkRepository, this);
        this.cListArtWorks = new CListArtWorks(artWorkRepository, this);
    }

    public void AddArtWorkClicked()
    {
        cAddArtWork.execute();
    }

    public void DeleteArtWorkClicked()
    {
        cDeleteArtWork.execute();
    }

    public void UpdateArtWorkClicked()
    {
        cUpdateArtWork.execute();
    }

    public void SearchArtWorkClicked()
    {
        cSearchArtWork.execute();
    }

    public void GenerateCSVFile()
    {
        cGenerateCSVFile.execute();
    }

    public void GenerateJSONFile()
    {
        cGenerateJSONFile.execute();
    }

    public void GenerateXMLFile()
    {
        cGenerateXMLFile.execute();
    }

    public void GenerateDOCFile()
    {
        cGenerateDOCFile.execute();
    }

    public void ListArtWorks()
    {
        cListArtWorks.execute();
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }
    public JList<String> getArtworks()
    {
        return list;
    }
    public void setArtworks(DefaultListModel<String> list)
    {
        this.listModel = list;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setType(String type) {
        this.type = type;
    }

}
