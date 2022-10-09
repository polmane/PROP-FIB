package FONTS.Controladors;

import FONTS.Classes.Directori;
import FONTS.Classes.Document;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CtrlDirectori {
    /**
     * Representa el directori que s'està utilitzant
     */
    private Directori directoriObert;

    /**
     * Representa el document carregat en el directori
     */
    private Document documentActiu;

    /**
     * Constructora
     */
    public CtrlDirectori() {
        directoriObert = null;
        documentActiu = null;
    }

    /**
     * Getters de directoriObert i documentActiu
     */
    public Directori getDirectoriObert() {
        return directoriObert;
    }
    public Document getDocumentActiu() {
        return documentActiu;
    }

    /**
     * Operació per crear un directori en el nostre sistema
     */
    public void crearDirectori(int idDir) {
        if (directoriObert == null) {
            directoriObert = new Directori(idDir);
        }
    }

     /**
     * Operació per obrir un document que ja teniem precarregat dins el nostre sistema
     * @param idDoc és l'identificador del docuemnt que volem obrir
     */
    public void recuperarDocument(int idDoc) {
        //nose si fa falta aquest if pk amb una interfície ben feta podem assegurar que el document existeix
        if (directoriObert.docs.containsKey(idDoc)) {
            documentActiu = directoriObert.docs.get(idDoc);
        }
    }


    /**
     * Modifica l'autor del document actiu
     * @param autor és el nou nom d'autor que es vol utilitzar pel document
     */
    public void modificarAutor(String autor) {
        documentActiu.setAutor(autor);
    }

    /**
     * Modifica el títol del document actiu
     * @param titol és el nou nom del títol que es vol utilitzar pel document
     */
    public void modificarTitol(String titol) {
        documentActiu.setTitol(titol);
    }

    /**
     * Modifica el contingut del document actiu
     * @param contingut és el nou contingut que es vol utilitzar pel document
     */
    public void modificarContngut(String contingut) {
        documentActiu.setContingut(contingut);
    }

    /**
     * Guarda un nou document al directori
     * //@param document és el document que es vol afegir al directori
     * -----------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /*
    public void guardarDocument(Document document) {
        //Comprovem que no tenim cap document amb el mateix autor i titol
        String autor = document.getAutor();
        String titol = document.getTitol();
        Set<Map.Entry<Integer, Document>> setDocuments = directoriObert.docs.entrySet();
        for (Map.Entry<Integer, Document> it_doc : setDocuments) {
            Document doc = it_doc.getValue();
            if (doc.getAutor().equals(autor) && doc.getTitol().equals(titol)) {
                System.err.println("ERROR: Ja existeix un document amb autor: '" + autor + "' i titol: '" + titol + "'.");
                return;
            }
        }

        // FIXME: Aquesta comparació és fumada?? Com garantim que l'identificador de la
        // classe Document i l'índex que ens és útil (idNouDoc) siguin el mateix?
        // No passa res si no ho són??
        int idDoc = document.getIdDoc();
        if (idDoc != directoriObert.getIdNouDoc()) {
            System.err.println("ERROR: La id del document no és l'esperada pel directori");
            return;
        }

        directoriObert.docs.put(directoriObert.getIdNouDoc(), document);

        //TODO: afegir pesos del nou document

        //Recalculem idNouDoc
        if (!directoriObert.deletedIds.isEmpty()) {
            directoriObert.setIdNouDoc(directoriObert.deletedIds.poll());
        } else {
            directoriObert.augmentaMaxIdDoc();
            directoriObert.setIdNouDoc(directoriObert.getMaxIdDoc());
        }
    }
    */

    /**
     * Exportar document falta acabar de moment tenim:
     * Triem si volem exportar en xml o txt
     * Creem un txt amb nom autor+titol, ara falta exportar-lo al local que nose com es fa
     * Ara he estat buscant i nose si el Writer et crea un File tal qual, haure de buscar més quan pugui
     */

    /*
    public void exportarDocument(String format, String path) {
        switch (format) {
            case "txt":
                try {
                    String nom = documentActiu.getAutor()+documentActiu.getTitol()+".txt";
                    Writer docExp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("nom"),"UTF-8"));
                    docExp.write(documentActiu.getAutor());
                    docExp.write("\n");
                    docExp.write(documentActiu.getTitol());
                    docExp.write("\n");
                    docExp.write(documentActiu.getContingut());
                } catch (Exception e) {
                    System.err.println("El document no s'ha creat correctament");
                    throw new RuntimeException(e);
                }
                break;
            default:
                break;
        }
    }
    */

    /**
     * Elimina un document del directori
     * @param idDoc és l'identificador del document que es vol eliminar del directori
     */
    public void eliminarDocument(int idDoc) {
        //Comprovem que idDoc sigui realment un identificador d'un document
        if (!directoriObert.docs.containsKey(idDoc)) {
            System.err.println("ERROR: No existeix el document amb identificador: " + idDoc + ".");
            return;
        }

        directoriObert.docs.remove(idDoc);

        //TODO: eliminar els pesos del document

        //Afegim l'id a la cua per poder ser reciclada
        directoriObert.deletedIds.add(idDoc);
    }
}
