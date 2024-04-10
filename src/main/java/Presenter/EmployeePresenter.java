package Presenter;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.*;
import java.util.List;


public class EmployeePresenter {
    private IEmployeeUI view;
    private ArtWorkRepository artWorkRepository;

    public EmployeePresenter(IEmployeeUI view) {
        this.view = view;
        this.artWorkRepository = new ArtWorkRepository();
    }
    public void fetchAndDisplayArtworks() {
        try {
            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            view.getListModel().clear();
            for (ArtWork artwork : artworks) {
                view.getListModel().addElement(artwork.toString());
            }
        } catch (Exception e) {
            showErrorMessage("Eroare la actualizarea listei." + e.getMessage());
            e.printStackTrace();
        }
    }

    public void onAddButtonClicked() {
        String titlu = view.getTextField1().trim();
        String artist = view.getTextField2().trim();
        int an;
        try {
            an = Integer.parseInt(view.getTextField3().trim());
        } catch (NumberFormatException e) {
            showErrorMessage("Anul trebuie să fie un număr întreg.");
            return;
        }
        String tip = view.getTextField4().trim();

        if (titlu.isEmpty() || artist.isEmpty() || tip.isEmpty()) {
            view.setTextField1("Trebuie completat titlul operei de arta!");
            view.setTextField2("Trebuie completat artistul operei de arta!");
            view.setTextField3("Trebuie completat anul crearii operei de arta!");
            view.setTextField4("Trebuie completat tipul operei de arta!");
            return;
        }

        ArtWork newArtWork = new ArtWork(titlu, artist, an, tip);
        try {
            artWorkRepository.addArtwork(newArtWork);
            showMessage("Opera de artă a fost adăugată cu succes.");
            fetchAndDisplayArtworks();
        } catch (Exception e) {
            showErrorMessage("Eroare la adăugarea operei de artă.");
            e.printStackTrace();
        }
    }

    public void onDeleteButtonClicked() {

        String titleToDelete = view.getTextField1().trim();
        if (titleToDelete.isEmpty()) {
            showMessage("Vă rugăm introduceți titlul operei de artă pentru ștergere.");
            view.setTextField1("Trebuie completat titlul operei de arta!");
            return;
        }

        int response = JOptionPane.showConfirmDialog((Component) view, "Sunteți sigur că doriți să ștergeți opera de artă cu titlul: " + titleToDelete + "?", "Confirmare ștergere", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            try {
                artWorkRepository.deleteArtwork(titleToDelete);
                showMessage("Opera de artă a fost ștearsă cu succes.");
                fetchAndDisplayArtworks();
            } catch (DAOException daoException) {
                showErrorMessage("Eroare la stergerea operei de arta.");
                daoException.printStackTrace();
            }
        }
    }

    public void onUpdateButtonClicked() {

        String title = view.getTextField1().trim();
        String artist = view.getTextField2().trim();
        int year = Integer.parseInt(view.getTextField3().trim());
        String type = view.getTextField4().trim();

        ArtWork artworkToBeUpdated;
        try {
            artworkToBeUpdated = artWorkRepository.getArtworkByName(title);

        } catch (DAOException e) {
            showErrorMessage("Eroare la căutarea operei de artă: " + e.getMessage());
            return;
        }

        if (artworkToBeUpdated != null) {
            artworkToBeUpdated.setArtist(artist);
            artworkToBeUpdated.setYear(year);
            artworkToBeUpdated.setType(type);

            try {
                artWorkRepository.updateArtwork(artworkToBeUpdated);
                showErrorMessage("Opera de artă a fost actualizată cu succes.");
                fetchAndDisplayArtworks();
            } catch (DAOException e) {
                showErrorMessage("A apărut o eroare la actualizarea operei de artă: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            showErrorMessage("Opera de artă cu titlul '" + title + "' nu a fost găsită.");
        }
    }

    public void onSearchButtonClicked() {
        String searchTitle = view.getTextField1().trim();
        if (searchTitle.isEmpty()) {
            view.setTextField1("Trebuie completat titlul operei de arta!");
            return;
        }
        try {
            ArtWork searchedArtwork = artWorkRepository.getArtworkByName(searchTitle);
            if (searchedArtwork == null) {
                showErrorMessage("Eroare la căutarea operei de artă: " + view.getTextField1());
            } else {
                String details = "Titlu: " + searchedArtwork.getTitle() +
                        "\nArtist: " + searchedArtwork.getArtist() +
                        "\nAn: " + searchedArtwork.getYear() +
                        "\nTip: " + searchedArtwork.getType();
                showArtWorkFoundDetails(details);
            }
        } catch (DAOException e) {
            showErrorMessage("Eroare la căutarea operei de artă");
            e.printStackTrace();
        }
    }

    public void onGenerateCsvButtonClicked() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvează ca...");
        fileChooser.setSelectedFile(new File("opere_arta.csv"));
        int userSelection = fileChooser.showSaveDialog((Component) view);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".csv")) {
                fileToSave = new File(fileToSave + ".csv");
            }
            generateCsv(fileToSave);
        }
    }

    public void onGenerateJSONButtonClicked() {
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
    private void generateCsv(File file) {
        try (PrintWriter pw = new PrintWriter(file)) {
            List<ArtWork> artworks = artWorkRepository.getAllArtworks();
            pw.println("ID,Titlu,Artist,An,Tip");
            for (ArtWork artwork : artworks) {
                pw.println(artwork.getId() + "," + artwork.getTitle() + "," + artwork.getArtist() + "," + artwork.getYear() + "," + artwork.getType());
            }
            showMessage("Fișierul CSV a fost generat cu succes.");
        } catch (DAOException | FileNotFoundException e) {
            showErrorMessage("Eroare la generarea fișierului CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void onGenerateXMLButtonClicked() {
        ArtWorkRepository artWorkRepository = new ArtWorkRepository();
        List<ArtWork> artworks = null;
        try {
            artworks = artWorkRepository.getAllArtworks();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("Artworks");
            doc.appendChild(rootElement);

            for (ArtWork art : artworks) {
                Element artwork = doc.createElement("ArtWork");
                rootElement.appendChild(artwork);

                Element title = doc.createElement("Title");
                title.appendChild(doc.createTextNode(art.getTitle()));
                artwork.appendChild(title);

                Element artist = doc.createElement("Artist");
                artist.appendChild(doc.createTextNode(art.getArtist()));
                artwork.appendChild(artist);

                Element year = doc.createElement("Year");
                year.appendChild(doc.createTextNode(String.valueOf(art.getYear())));
                artwork.appendChild(year);

                Element type = doc.createElement("Type");
                type.appendChild(doc.createTextNode(art.getType()));
                artwork.appendChild(type);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save XML File");
            fileChooser.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));
            fileChooser.setSelectedFile(new File("artworks.xml"));

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave.getPath().toLowerCase().endsWith(".xml")) {
                    fileToSave = new File(fileToSave + ".xml");
                }
                StreamResult result = new StreamResult(fileToSave);

                transformer.transform(source, result);
                JOptionPane.showMessageDialog(null, "XML file has been saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ParserConfigurationException | TransformerException e) {
            JOptionPane.showMessageDialog(null, "Error writing XML file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void onGenerateSimpleDocButtonClicked() {
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

    public void showArtWorkFoundDetails(String details) {
        JOptionPane.showMessageDialog((Component) view, details, "Detalii Opera de Artă", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog((Component) view, message, "Eroare", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog((Component) view, message, "Informatie", JOptionPane.INFORMATION_MESSAGE);
    }

}
