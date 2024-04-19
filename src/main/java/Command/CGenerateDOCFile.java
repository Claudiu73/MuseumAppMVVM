package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import ViewModel.EmployeeViewModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CGenerateDOCFile implements ICommand{

    private ArtWorkRepository artWorkRepository;
    private EmployeeViewModel viewModel;
    public CGenerateDOCFile(ArtWorkRepository artWorkRepository, EmployeeViewModel viewModel)
    {
        this.artWorkRepository = artWorkRepository;
        this.viewModel = viewModel;
    }
    @Override
    public void execute() {
        ArtWorkRepository artWorkRepository = new ArtWorkRepository();
        List<ArtWork> artworks = null;
        try {
            artworks = artWorkRepository.getAllArtworks();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save as Simple Word Document");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Word Files", "doc"));
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getPath().toLowerCase().endsWith(".doc")) {
                fileToSave = new File(fileToSave.getPath() + ".doc");
            }

            try (FileWriter writer = new FileWriter(fileToSave)) {
                for (ArtWork art : artworks) {
                    writer.write("Titlu: " + art.getTitle() + "\n");
                    writer.write("Artist: " + art.getArtist() + "\n");
                    writer.write("An: " + art.getYear() + "\n");
                    writer.write("Tip: " + art.getType() + "\n");
                    writer.write("\n");
                }
                JOptionPane.showMessageDialog(null, "Documentul a fost salvat cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Eroare la scrierea în fișier: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
