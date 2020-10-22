// IPPO Assignment 1, Version 20.3, 21/10/2020
package ippo.assignment1.proxy;

import ippo.assignment1.library.Picture;
import ippo.assignment1.library.service.Service;
import ippo.assignment1.library.service.ServiceFromProperties;
import ippo.assignment1.library.utils.HashMap;

/**
 * A skeleton cache proxy service for the PictureViewer application.
 * The skeleton does no implement any caching!
 *
 * @author Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 20.3, 21/10/2020
 */
public class CacheProxy implements Service {

    private Service baseService = null;
    private final HashMap<String, Picture> memo = new HashMap<>();

    /**
     * Return a textual name for the service.
     *
     * @return the name of the base service
     */
    public String serviceName() {
        return baseService.serviceName();
    }

    /**
     * Create a proxy service based on the service specified in the
     * <code>proxy.cache.service</code> resource.
     */
    public CacheProxy() {
        baseService = new ServiceFromProperties("proxy.cache.service");
    }

    /**
     * Create a proxy service based on the specified service.
     *
     * @param baseService the base service
     */
    public CacheProxy(Service baseService) {
        this.baseService = baseService;
    }

    /**
     * Return a picture from the base service.
     *
     * @param subject the free-text subject string
     * @param index   the index of the matching picture to return
     * @return the requested picture
     */
    public Picture getPicture(String subject, int index) {
        // if this exists in our memo
        // then we can directly return it
        //
        // (note): in production we preferred to use hashed key
        // cuz the raw string may be too long for hash map's key
        // so we can use some hash method such as md5/sha1/sha256 ...
        // and these method can convert ANY length string to a FIXED length string
        // so it's good for our case
        if (memo.containsKey(subject)) {
            return memo.get(subject);
        }

        // otherwise we perform a fresh get
        // and save it to out memo
        //
        // (note): we dont use else clause here because we just dont need it
        // if condition above satisfied then we have been returned
        // otherwise it will goto below code
        // this trick can be very elegant on production-ready code
        // (especially w/ Python...)
        Picture picture = baseService.getPicture(subject, index);
        memo.put(subject, picture);
        return picture;
    }
}
