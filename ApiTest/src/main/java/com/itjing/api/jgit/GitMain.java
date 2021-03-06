package com.itjing.api.jgit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * https://github.com/centic9/jgit-cookbook
 */
@Slf4j
public class GitMain {

    private File localGitDir = new File("D:\\project\\sanri-tools-maven");
//    private File localGitDir = new File("D:\\project\\company\\scp-st-messagecenter-component");

    /**
     *
     * @throws IOException
     */
    @Test
    public void currentBranch() throws IOException {
        Git open = Git.open(localGitDir);
        Repository repository = open.getRepository();
        String branch = repository.getBranch();
        System.out.println(branch);
    }

    @Test
    public void listBranchs() throws IOException, GitAPIException {
        Git open = Git.open(localGitDir);

        // git branch
        List<Ref> call = open.branchList().call();
        Iterator<Ref> iterator = call.iterator();
        while (iterator.hasNext()){
            Ref ref = iterator.next();
            System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
        }

        // git branch -a
        System.out.println("\n------------- includes remote branchs in here:\n");
        call = open.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
        Iterator<Ref> refIterator = call.iterator();
        while (refIterator.hasNext()){
            Ref ref = refIterator.next();
            System.out.println("Branch: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
        }
    }

    /**
     * ???????????? ,?????????????????????????????????
     * git checkout nacos
     * @throws IOException
     * @throws GitAPIException
     */
    @Test
    public void checkout() throws IOException, GitAPIException {
        Git git = Git.open(localGitDir);
        String branchName = "nacos";
        git.checkout().setCreateBranch(false).setName(branchName).call();
    }

    /**
     * ???????????? dev ??????
     * git checkout -b dev origin/dev
     * @throws IOException
     * @throws GitAPIException
     */
    @Test
    public void remoteCheckout() throws IOException, GitAPIException {
        Git git = Git.open(localGitDir);
        String branchName = "dev";
        git.checkout().setCreateBranch(true).setName(branchName).call();
    }

    /**
     * ?????????????????????????????????????????????,???????????????????????? key ??????
     * @throws IOException
     * @throws GitAPIException
     *
     * git pull
     */
    @Test
    public void pull() throws IOException, GitAPIException {
        Git git = Git.open(localGitDir);
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =  null;
        git.pull()
                .setCredentialsProvider(usernamePasswordCredentialsProvider)
                .call();
    }

    /**
     * ???????????????????????????,?????????????????? ,??????????????????,????????????
     */
    @Test
    public void commit() throws IOException, GitAPIException {
        Git git = Git.open(localGitDir);

        // git add
        DirCache testfile = git.add().addFilepattern("testfile").setUpdate(true).call();

        // git commit
        git.commit().setMessage("????????????").call();
    }

    /**
     * git push origin dev
     * @throws IOException
     * ?????????????????????,????????????????????????,???????????????
     */
    @Test
    public void push() throws IOException, GitAPIException {
        Git git = Git.open(localGitDir);
        git.push().setRemote("").call();
    }

    /**
     * git tag
     *
     * @throws IOException
     * @throws GitAPIException
     */
    @Test
    public void tagsAndTagCommits() throws IOException, GitAPIException {
        Git git = Git.open(localGitDir);
        Repository repository = git.getRepository();

        List<Ref> call = git.tagList().call();
        for (Ref ref : call) {
            System.out.println("Tag: " + ref.getName() + " " + ref.getObjectId().getName());

            LogCommand log = git.log();
            Ref peelRef = repository.getRefDatabase().peel(ref);
            // peelRef ???
            ObjectId peeledObjectId = peelRef.getPeeledObjectId();
//            System.out.println(peeledObjectId+" |||||||");
            if(peeledObjectId != null){
                log.add(peeledObjectId);
            }else{
                log.add(ref.getObjectId());
            }
            Iterable<RevCommit> revCommits = log.call();
            for (RevCommit revCommit : revCommits) {
                System.out.println("\t\t Commit: " + revCommit /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
            }

//            break;
        }
    }

    @Test
    public void logs() throws IOException, GitAPIException {
        Git git = Git.open(localGitDir);
        Repository repository = git.getRepository();

        Iterable<RevCommit> logs = git.log()
                .call();
        int count = 0;
        for (RevCommit rev : logs) {
            //System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
            count++;
        }
        System.out.println("Had " + count + " commits overall on current branch");

        logs = git.log()
                .add(repository.resolve("remotes/origin/testbranch"))
                .call();
        count = 0;
        for (RevCommit rev : logs) {
            System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
            count++;
        }
        System.out.println("Had " + count + " commits overall on test-branch");

        logs = git.log()
                .not(repository.resolve("master"))
                .add(repository.resolve("remotes/origin/testbranch"))
                .call();
        count = 0;
        for (RevCommit rev : logs) {
            System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
            count++;
        }
        System.out.println("Had " + count + " commits only on test-branch");

        logs = git.log()
                .all()
                .call();
        count = 0;
        for (RevCommit rev : logs) {
            //System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
            count++;
        }
        System.out.println("Had " + count + " commits overall in repository");

        logs = git.log()
                // for all log.all()
                .addPath("README.md")
                .call();
        count = 0;
        for (RevCommit rev : logs) {
            //System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
            count++;
        }
        System.out.println("Had " + count + " commits on README.md");

        logs = git.log()
                // for all log.all()
                .addPath("pom.xml")
                .call();
        count = 0;
        for (RevCommit rev : logs) {
            //System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
            count++;
        }
        System.out.println("Had " + count + " commits on pom.xml");

        // all commits
        Iterable<RevCommit> call = git.log().all().call();
    }

    @Test
    public void diff() throws IOException, GitAPIException {
        Git git = Git.open(localGitDir);
        Repository repository = git.getRepository();

        listDiff(repository,git,"f932d5c4187694f463285686afb548989e9c9b9d","8263413f7d938331bc91652f603560f8124ca204");
    }

    private static void listDiff(Repository repository, Git git, String oldCommit, String newCommit) throws GitAPIException, IOException {
        final List<DiffEntry> diffs = git.diff()
                .setOldTree(prepareTreeParser(repository, oldCommit))
                .setNewTree(prepareTreeParser(repository, newCommit))
                .call();

        System.out.println("Found: " + diffs.size() + " differences");
        for (DiffEntry diff : diffs) {
            System.out.println("Diff: " + diff.getChangeType() + ": " +
                    (diff.getOldPath().equals(diff.getNewPath()) ? diff.getNewPath() : diff.getOldPath() + " -> " + diff.getNewPath()));
        }
    }

    private static AbstractTreeIterator prepareTreeParser(Repository repository, String objectId) throws IOException {
        // from the commit we can build the tree which allows us to construct the TreeParser
        //noinspection Duplicates
        try (RevWalk walk = new RevWalk(repository)) {
            RevCommit commit = walk.parseCommit(repository.resolve(objectId));
            RevTree tree = walk.parseTree(commit.getTree().getId());

            CanonicalTreeParser treeParser = new CanonicalTreeParser();
            try (ObjectReader reader = repository.newObjectReader()) {
                treeParser.reset(reader, tree.getId());
            }

            walk.dispose();

            return treeParser;
        }
    }

    /**
     * sanritoolsmaven ???????????????
     */
    @Test
    public void generateUpdatePackage () throws IOException, GitAPIException, InterruptedException {
        // ????????? mvn clean compile
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        processBuilder.command("cmd", "/c", "mvn", "-DskipTests=true", "clean", "compile", "-f", "pom.xml");
//        Process process = processBuilder.start();
//        process.waitFor();

        // ?????????????????????
        File patch = new File("d:/test/patch"+System.currentTimeMillis());patch.mkdirs();
        System.out.println("???????????????:"+patch);
        File updateEntry = new File(patch,"updateEntry");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(updateEntry),"utf-8");

        Git git = Git.open(localGitDir);
        Repository repository = git.getRepository();
        final List<DiffEntry> diffs = git.diff()
                .setOldTree(prepareTreeParser(repository, "306d76760d2b5e789b09f8e8815988917448ed69"))      // ???????????????????????????, ??????????????????
                .setNewTree(prepareTreeParser(repository, ""))
                .call();

        for (DiffEntry diff : diffs) {
            DiffEntry.ChangeType changeType = diff.getChangeType();
            String newPath = diff.getNewPath();
            String oldPath = diff.getOldPath();

            try {
                File changes = new File(patch, "changes");
                if(changeType == DiffEntry.ChangeType.ADD) {
                    copyPatchFile(changeType,changes, newPath,outputStreamWriter);
                }else if(changeType == DiffEntry.ChangeType.MODIFY || changeType == DiffEntry.ChangeType.DELETE){
                    copyPatchFile(changeType,changes,oldPath,outputStreamWriter);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        outputStreamWriter.close();
    }

    /**
     * ?????????????????????
     * @param changeType
     * @param patch
     * @param relativePath
     * @param outputStreamWriter
     */
    private void copyPatchFile(DiffEntry.ChangeType changeType, File patch, String relativePath, OutputStreamWriter outputStreamWriter) throws IOException {
        log.info("[{}]????????????[{}]",changeType,relativePath);

        File classes = new File(localGitDir, "target/classes");
        File resourceRoot = new File(localGitDir,"src/main/resources");

        String fileName = new File(relativePath).getName();
        if("pom.xml".equals(fileName)){
            log.info("???????????? pom.xml ,?????????????????? pom ??????????????????,????????????????????????????????? ");
            return ;
        }

        final String javaPathPrefix = "src/main/java";
        final String resourcesPathPrefix = "src/main/resources";
        final String webappPathPrefix = "src/main/webapp";

        if (relativePath.startsWith(javaPathPrefix)) {
            // java ??????,?????????????????????,??????????????????????????????
            Path javaPath = Paths.get(javaPathPrefix).relativize(Paths.get(relativePath));
            File sourceFile = new File(classes, javaPath.toString());
            File parentDir = sourceFile.getParentFile();
            String baseName = FilenameUtils.getBaseName(fileName);

            // ??????????????????
            Path normalize = javaPath.getParent().normalize();
            String classPath = normalize.toString().replaceAll("\\\\", "/");
            outputStreamWriter.write(changeType + " WEB-INF/classes/"+classPath+"/"+baseName+".class" + '\n');
            // ???????????????,????????????????????????
            if(changeType == DiffEntry.ChangeType.DELETE){
                return ;
            }

            // ???????????????????????????,???????????????
            WildcardFileFilter wildcardFileFilter = new WildcardFileFilter(baseName+"$*.class");
            OrFileFilter orFileFilter = new OrFileFilter(wildcardFileFilter, new NameFileFilter(baseName+".class"));
            Collection<File> files = FileUtils.listFiles(parentDir, orFileFilter, TrueFileFilter.TRUE);

            // ????????????????????????,???????????????
            File targetDir = new File(patch, "WEB-INF/classes");
            File classFilesDir = new File(targetDir, javaPath.getParent().toString());
            classFilesDir.mkdirs();
            for (File file : files) {
                FileUtils.copyFile(file, new File(classFilesDir, file.getName()));
            }

            return ;
        }
        if(relativePath.startsWith(resourcesPathPrefix)){
            // ??????????????????
            Path resourcesPath = Paths.get(resourcesPathPrefix).relativize(Paths.get(relativePath));
            File sourceFile = new File(classes, resourcesPath.toString());

            // ??????????????????
            Path normalize = resourcesPath.getParent().normalize();
            String classPath = normalize.toString().replaceAll("\\\\", "/");
            outputStreamWriter.write(changeType + " WEB-INF/classes/"+classPath+"/"+fileName + '\n');

            // ???????????????,????????????????????????
            if(changeType == DiffEntry.ChangeType.DELETE){
                return ;
            }

            // ??????????????????
            File targetDir = new File(patch, "WEB-INF/classes");
            File targetFilePath = new File(targetDir, resourcesPath.getParent().toString());
            targetFilePath.mkdirs();

            // ???????????????????????????
            FileUtils.copyFile(sourceFile,new File(targetFilePath,sourceFile.getName()));

            return ;
        }

        if(relativePath.startsWith(webappPathPrefix)){
            // ?????? webapp ??????
            File sourceFile = new File(localGitDir, relativePath);

            // ??????????????????
            Path webappPath = Paths.get(webappPathPrefix).relativize(Paths.get(relativePath));
            File targetPath = new File(patch, webappPath.getParent().toString());targetPath.mkdirs();

            // ??????????????????
            outputStreamWriter.write(changeType+" "+webappPath + '\n');
            // ???????????????,????????????????????????
            if(changeType == DiffEntry.ChangeType.DELETE){
                return ;
            }

            // ????????????
            FileUtils.copyFile(sourceFile,new File(targetPath,fileName));

            return ;
        }

        log.warn("????????????[{}]??????????????????,????????????",relativePath);
    }

    @Test
    public void test(){
        File file = new File("D:\\project\\sanri-tools-maven\\target\\classes\\com\\sanri\\app\\servlet");
        WildcardFileFilter wildcardFileFilter = new WildcardFileFilter("CompanyServlet$*.class");
        OrFileFilter orFileFilter = new OrFileFilter(wildcardFileFilter, new NameFileFilter("CompanyServlet.class"));
        Collection<File> files = FileUtils.listFiles(file, orFileFilter, TrueFileFilter.TRUE);
        for (File file1 : files) {
            System.out.println(file1);
        }
    }

    @Test
    public void pathTest(){
        String path = "\\project\\sanri-tools-maven\\target\\classes\\com\\sanri\\app\\servle";
        System.out.println(Paths.get(path).toString().replaceAll("\\\\","/"));
    }

    /**
     * ???????????????,?????????????????????,?????? idea ???????????????; ?????????????????????
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testMvn() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        File file = new File(localGitDir, "pom.xml");
        File settting = new File("C:\\Users\\091795960\\.m2/settings.xml");
        processBuilder.command("cmd", "/c", "mvn","-s",settting.getAbsolutePath(), "-DskipTests=true", "clean", "compile", "-f", file.getAbsolutePath());
        Process process = processBuilder.start();
        BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream(),"utf-8"));
        new Thread(){
            @Override
            public void run() {
                String line = null;
                try {
                    while ((line = inputStreamReader.readLine()) != null){
                        System.out.println(line);
                    }
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        int waitFor = process.waitFor();
        process.destroy();
    }
}
