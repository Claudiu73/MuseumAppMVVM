package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import ViewModel.EmployeeViewModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CGenerateJSONFile implements ICommand
{
    private ArtWorkRepository artWorkRepository;
    private EmployeeViewModel viewModel;

    public CGenerateJSONFile(ArtWorkRepository artWorkRepository, EmployeeViewModel viewModel)
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

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");
        for (int i = 0; i < artworks.size(); i++) {
            ArtWork art = artworks.get(i);
            jsonBuilder.append("  {\n");
            jsonBuilder.append(String.format("    \"title\": \"%s\",\n", art.getTitle()));
            jsonBuilder.append(String.format("    \"artist\": \"%s\",\n", art.getArtist()));
            jsonBuilder.append(String.format("    \"year\": %d,\n", art.getYear()));
            jsonBuilder.append(String.format("    \"type\": \"%s\"\n", art.getType()));
            jsonBuilder.append(i < artworks.size() - 1 ? "  },\n" : "  }\n");
        }
        jsonBuilder.append("]");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save JSON File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON Files", "json"));
        fileChooser.setSelectedFile(new File("artworks.json"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getPath().toLowerCase().endsWith(".json")) {
                fileToSave = new File(fileToSave + ".json");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(jsonBuilder.toString());
                writer.flush();
                JOptionPane.showMessageDialog(null, "JSON file has been saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error writing JSON file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
