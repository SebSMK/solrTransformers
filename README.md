#SMK Transformers

##How to configure the SMK solr transformers

The host, path and transformer names shown below are just examples, and will of course have to be replaced with the correct values for the instance of solr being configured.

1. Build the jar (note that the path is not the project root)
```sh
$ cd git/solrTransformers/solr-transformer
$ mvn install
```	
2. Copy the jar to solr
```sh
$ scp target/solr-transformer-1.1.061-SNAPSHOT.jar root@csdev-seb:/opt/solr/example/solr_ENB/lib/
```
3. Edit solr config
```sh
$ vi /opt/solr/example/solr_ENB/dev_collectionspace/conf/data-config.xml
```
data-config.xml :
```xml
	<dataConfig>
	  <document>
	    <entity
	      name="Collection"
	      processor="SolrEntityProcessor"
	                url="http://172.20.1.73:8080/solr/prod_SQL_full_export"
	                query="-id:eks*"
	                fl="  *, old_version:_version_"
	
	      transformer="org.smk.solr.transformer.enb.Process_enb_clean"
	      >
	    </entity>
	
	  </document>
	</dataConfig>
```


