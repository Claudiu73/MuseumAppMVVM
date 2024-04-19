package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import ViewModel.EmployeeViewModel;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;

public class CGenerateCSVFile implements ICommand{

    private EmployeeViewModel viewModel;
    private ArtWorkRepository artWorkRepository;

    public CGenerateCSVFile(ArtWorkRepository artWorkRepository, EmployeeViewModel viewModel)
    {
        this.artWorkRepository = artWorkRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvează ca...");
        fileChooser.setSelectedFile(new File("opere_arta.csv"));
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".csv")) {
                fileToSave = new File(fileToSave + ".csv");
            }
            try (PrintWriter pw = new PrintWriter(fileToSave)) {
            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            pw.println("ID,Titlu,Artist,An,Tip");
            for (ArtWork artwork : artworks) {
                pw.println(artwork.getId() + "," + artwork.getTitle() + "," + artwork.getArtist() + "," + artwork.getYear() + "," + artwork.getType());
            }
        } catch (DAOException | FileNotFoundException e) {
            e.printStackTrace();
        }
        }
    }
}
