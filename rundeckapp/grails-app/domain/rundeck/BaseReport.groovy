package rundeck

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class BaseReport {

    String node
    String title
    String status
    String actionType
    String ctxProject
    String ctxType
    String ctxName
    String maprefUri
    String reportId
    String tags
    String author
    Date dateStarted
    Date dateCompleted 
    String message

    static mapping = {
        def config = ConfigurationHolder.config
        if (config.rundeck.v14.rdbsupport == 'true') {
            message type: 'text'
            title type: 'text'
        }
    }
   static constraints = {
        reportId(nullable:true)
        tags(nullable:true)
        node(nullable:true)
        maprefUri(nullable:true)
        ctxName(nullable:true)
        ctxType(nullable:true)
        status(nullable:false,inList:['succeed','fail','cancel'])
        actionType(nullable:false,inList:['create','update','delete','succeed','fail','cancel'])
    }
    public static final ArrayList<String> exportProps = [
            'node',
            'title',
            'status',
            'actionType',
            'ctxProject',
            'ctxType',
            'ctxName',
            'reportId',
            'tags',
            'author',
            'message',
            'dateStarted',
            'dateCompleted'
    ]

    def Map toMap(){
        this.properties.subMap(exportProps)
    }

    static buildFromMap(BaseReport obj, Map data) {
        data.each {k, v ->
            obj[k] = v
        }
    }

    static BaseReport fromMap(Map data) {
        def BaseReport report = new BaseReport()
        buildFromMap(report, data.subMap(exportProps))
        report
    }
}
