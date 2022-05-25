import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.*;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import java.util.*;
import java.io.*;

class search
{
	public static void main(String args[])throws IOException,ParseException
	{
		Directory dir=FSDirectory.open(new File("e:test\\demoproject\\vms"));
		IndexReader reader=IndexReader.open(dir);
		IndexSearcher sear=new IndexSearcher(reader);
		QueryParser parser=new QueryParser("fieldname",new WhitespaceAnalyzer());
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
		Query query=parser.parse(s);


		Sort sort = new Sort(SortField.FIELD_SCORE, new SortField("fieldname", SortField.Type.STRING,true));
        	TopDocs docs=sear.search(query,10,sort);
       		ScoreDoc[] result=docs.scoreDocs;
        	int result_size=docs.totalHits;
        	System.out.println("no_of_hits : "+result_size);
        	for(int i=0;i<result_size;i++)
        	{
			Document d= sear.doc(result[i].doc);
			System.out.println("document name :"+d.get("file_name"));
        	}
	}
}
		
