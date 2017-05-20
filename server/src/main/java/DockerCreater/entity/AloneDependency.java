package DockerCreater.entity;

/**
 * Created by Jon on 2017/5/4.
 */
public class AloneDependency implements Comparable<AloneDependency> {
    private String name;
    private String version;
    private String architecture;

    public AloneDependency() {
    }

    public AloneDependency(String name) {
        this.architecture = "default";
        this.name = name;
        this.version = "1.0";
    }

    public AloneDependency(String name, String version, String architecture) {
        this.name = name;
        this.architecture = architecture;
        this.version = version;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return this.getName()+"_"+this.getVersion()+"_"+this.getArchitecture();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass()!=obj.getClass())
            return false;
        AloneDependency temp= (AloneDependency)obj;
        if (this.getArchitecture().equals(temp.getArchitecture()) &&
                this.getName().equals(temp.getName()) &&
                this.getVersion().equals(temp.getVersion()))
            return true;
        return false;
    }

    @Override
    public int compareTo(AloneDependency aDep){
        return this.name.compareTo(aDep.getName());
    }
}
