package ru.nartov.component;

import java.util.Collection;

public interface FileArrayOperation {

    <T> Object executeOperation(Collection<T> collection);

    String getJsonFieldName();
}
