package com.rkubyshkin.storage;

import com.rkubyshkin.exception.StorageException;
import com.rkubyshkin.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
   private File directoryOfResumes;

    protected AbstractFileStorage(File directoryOfResumes) {
        Objects.requireNonNull(directoryOfResumes, "directoryOfResume must not be null");
        if(!directoryOfResumes.isDirectory()) {
            throw new IllegalArgumentException(directoryOfResumes.getAbsolutePath() + " is not directory");
        }
        if(!directoryOfResumes.canRead() || !directoryOfResumes.canWrite()) {
            throw new IllegalArgumentException(directoryOfResumes.getAbsolutePath() + " is not readable or writeable");
        }
        this.directoryOfResumes = directoryOfResumes;
    }

    protected abstract void doWrite(Person r, File file) throws IOException;

    protected abstract Person doRead(File file) throws IOException;

    @Override
    protected List<Person> doGetAllSorted() {
        List<Person> listOfPersons= new ArrayList<>();
        for (File a : directoryOfResumes.listFiles()) {
            try {
                Person person = doRead(a);
                listOfPersons.add(person);
            } catch (IOException e) {
                throw new StorageException("IO ERROR", a.getName(), e);
            }
        }
        return listOfPersons;
    }

    @Override
    protected Person doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO ERROR", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Person r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO ERROR", file.getName(), e);
        }
    }

    @Override
    protected File getSearchKey(String uid) {
        return new File(directoryOfResumes, uid);
    }

    @Override
    protected void doUpdate(Person r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO ERROR", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doDelete(File file) {
        file.getAbsoluteFile().delete();
    }

    @Override
    public void clear() {
        for (File a: Objects.requireNonNull(directoryOfResumes.listFiles())) {
            if(!a.isDirectory()) {
                a.getAbsoluteFile().delete();
            }
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directoryOfResumes.listFiles()).length;
    }
}
