package viana.nunes.henrique.caio.screenAz.service;

public interface IConvertsData {
    <T> T getData(String json, Class<T> classT);
}
