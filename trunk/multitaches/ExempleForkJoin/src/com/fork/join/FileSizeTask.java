package com.fork.join;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

//l'idée du fork/join est de déviser l'éxécution des taches "fork" avant de les rassembler "fork". très utiles sur les traitements récursives 
//pour utiliser la notion fork/join, il faut hériter de RecursiveTask<V>. utiliser la méthode ForkJoinTask pour le fork et le join.
public class FileSizeTask extends RecursiveTask<Long> {
    private File root;
    public FileSizeTask(File root) { this.root = root; }
    
    //calcul la taille du dossier root
    public Long compute() {
        List<ForkJoinTask<Long>> subTasks = new ArrayList<ForkJoinTask<Long>>();
        long size = 0;
        File[] files = root.listFiles();
        for(File f : files) {
            if (f.isDirectory()) {
                subTasks.add(new FileSizeTask(f).fork());
            } else {
                size += f.length();
            }
        }
        for (ForkJoinTask<Long> subTask : subTasks) {size += subTask.join();}
        return size;
    }
}
