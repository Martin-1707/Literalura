package com.aluraChallen.literalura.Service;

public interface IConvertirDatos {
    <T> T obtenerDatosJSON(String json, Class<T> clase);
}
