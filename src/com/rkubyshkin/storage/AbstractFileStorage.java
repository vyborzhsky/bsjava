package com.rkubyshkin.storage;

import com.rkubyshkin.exception.StorageException;
import com.rkubyshkin.model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
   private File directoryOfResumes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractFileStorage that = (AbstractFileStorage) o;
        return directoryOfResumes.equals(that.directoryOfResumes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directoryOfResumes);
    }

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

    protected abstract void doWrite(Person r, OutputStream outStream) throws IOException;

    protected abstract Person doRead(InputStream inStream) throws IOException;

    @Override
    protected List<Person> doGetAllSorted() {
        List<Person> listOfPersons= new ArrayList<>();
        for (File a : directoryOfResumes.listFiles()) {
            try {
                Person person = doRead(new BufferedInputStream(new FileInputStream(a)));
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
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Can't read file, ERROR", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Person r, File file) {
        try {
            file.createNewFile();
            doUpdate(r, file);
        } catch (IOException e) {
            throw new StorageException("Can't create file, ERROR", file.getName(), e);
        }
    }

    @Override
    protected File getSearchKey(String uid) {
        return new File(directoryOfResumes, uid);
    }

    @Override
    protected void doUpdate(Person r, File file) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Can't write file, ERROR", r.getUid(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doDelete(File file) {
        if(!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    public void clear() {
        for (File a: Objects.requireNonNull(directoryOfResumes.listFiles())) {
            if(!a.isDirectory()) {
                doDelete(a);
            }
        }
    }

    @Override
    public int size() {
        String[] list = directoryOfResumes.list();
        if(list == null) {
            throw new StorageException("Directory error read ", null);
        }
        return list.length;
    }
}
