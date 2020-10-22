// IPPO Assignment 1, Version 20.3, 21/10/2020
package ippo.assignment1.controller;

import ippo.assignment1.library.Picture;
import ippo.assignment1.library.controller.Controller;
import ippo.assignment1.library.service.Service;
import ippo.assignment1.library.service.ServiceFromProperties;
import ippo.assignment1.library.utils.HashMap;
import ippo.assignment1.library.utils.Properties;
import ippo.assignment1.library.view.View;
import ippo.assignment1.library.view.ViewFromProperties;

/**
 * A simple controller for the PictureViewer application.
 *
 * @author Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 20.3, 21/10/2020
 */
public class PropertyController implements Controller {

    private View view;
    private Service service;

    private final HashMap<Integer, String> ippo_hm = new HashMap<>();

    /**
     * Start the controller.
     */
    public void start() {

        // create the view and the service objects
        view = new ViewFromProperties(this);
        service = new ServiceFromProperties();

        String[] subjects = Properties.get("controller.subjects").split(",");
        for (String subject : subjects) {
            addSubject(subject.strip(), view);
        }

        // start the interface
        view.start();
    }

    /**
     * Handle the specified selection from the interface.
     *
     * @param selectionID the id of the selected item
     */
    public void select(int selectionID) {

        // a picture corresponding to the selectionID
        // by default, this is an empty picture
        // (this is used if the selectionID does not match)
        // create a picture corresponding to the selectionID
        Picture picture = service.getPicture(ippo_hm.get(selectionID), 1);

        // show the picture in the interface
        view.showPicture(picture);
    }

    private void addSubject(String name, View view) {
        ippo_hm.put(view.addSelection(name), name);
    }
}
