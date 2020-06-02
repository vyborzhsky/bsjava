package com.rkubyshkin.storage;

import com.rkubyshkin.exception.StorageException;
import com.rkubyshkin.model.Person;
import com.rkubyshkin.storage.serialize.SerializerStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
   private Path directoryOfResumes;
    private SerializerStream serializerStream;

    protected AbstractPathStorage(String dir,SerializerStream serializerStream) {
        this.serializerStream = serializerStream;
        Objects.requireNonNull(dir, "directoryOfResume must not be null");
        directoryOfResumes = Paths.get(dir);

        if(!Files.isDirectory(directoryOfResumes) || !Files.isWritable(directoryOfResumes)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
    }
    @Override
    protected List<Person> doGetAllSorted() {
       return getFileListStream().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    protected Person doGet(Path file) {
        try {
            return serializerStream.doRead(new BufferedInputStream(Files.newInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Can't read file, ERROR", file.getFileName().toString(), e);
        }
    }

    @Override
    protected void doSave(Person r, Path file) {
        try {
            Files.createFile(file);
        } catch (IOException e) {
            throw new StorageException("Can't create path, ERROR " + file, file.getFileName().toString(), e);
        }
        doUpdate(r, file);
    }

    @Override
    protected Path getSearchKey(String uid) {
        return directoryOfResumes.resolve(uid);
    }

    @Override
    protected void doUpdate(Person r, Path file) {
        try {
            serializerStream.doWrite(r, new BufferedOutputStream(Files.newOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Can't write file, ERROR", r.getUid(), e);
        }
    }

    @Override
    protected boolean isExist(Path file) {
        return Files.isRegularFile(file);
    }

    @Override
    protected void doDelete(Path file) {
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new StorageException("File delete error", file.getFileName().toString());
        }
    }

    @Override
    public void clear() {
        getFileListStream().forEach(this::doDelete);

    }

    @Override
    public int size() {
        return (int) getFileListStream().count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPathStorage that = (AbstractPathStorage) o;
        return directoryOfResumes.equals(that.directoryOfResumes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directoryOfResumes);
    }

    private Stream<Path> getFileListStream() {
        try {
            return Files.list(directoryOfResumes);
        } catch (IOException e) {
            throw new StorageException("Directory read error", e);
        }
    }
}
