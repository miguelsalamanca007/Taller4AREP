package edu.escuelaing.arep.app.service;

import edu.escuelaing.arep.app.service.impl.HttpResponse;

public interface HTTPOperation {
    String handle(String request, HttpResponse response);
}
