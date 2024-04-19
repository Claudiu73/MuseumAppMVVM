package Command;

import Model.ArtWork;
import Repo.ArtWorkRepository;
import Repo.DAOException;
import ViewModel.EmployeeViewModel;
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
import java.io.File;
import java.util.List;

public class CGenerateXMLFile implements ICommand{

    private ArtWorkRepository artWorkRepository;
    private EmployeeViewModel viewModel;

    public CGenerateXMLFile(ArtWorkRepository artWorkRepository, EmployeeViewModel viewModel)
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
}
