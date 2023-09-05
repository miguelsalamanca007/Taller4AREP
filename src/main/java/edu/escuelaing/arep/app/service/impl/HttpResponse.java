package edu.escuelaing.arep.app.service.impl;

/**
 * Class corresponding to the Http Server Responses.
 * By default, it is a Text/HTML response.
 */
public class HttpResponse {
    private String type;
    private String body;
    private String status;

    /**
     * Sets the type of the Http message according to the file extension
     * @param file
     */
    public void setSpecificType(String file) {
        String extension = file.split("\\.")[1];
        if (extension.equalsIgnoreCase("html")) {
            type = "Content-type: text/html";
        } else if (extension.equalsIgnoreCase("css")) {
            type = "Content-type: text/css";
        } else if (extension.equalsIgnoreCase("js")) {
            type = "Content-type: application/javascript";
        } else if (extension.equalsIgnoreCase("png")) {
            type = "Content-type: image/png";
        }
    }

    public String type() {
        return type;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return this.body;
    }

    public String getStatus() {
        return this.status;
    }

    public void setTypeForResponse(String type) {
        this.type = "Content-type: " + type;
    }

}
