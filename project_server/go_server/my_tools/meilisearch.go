package my_tools

import (
	"fmt"
	"go_server/model"

	"github.com/meilisearch/meilisearch-go"
)

var Meili = &meili{}

type meili struct {
	cli meilisearch.ServiceManager
}

func (ml *meili) Init(host, apiKey string) {
	ml.cli = meilisearch.New(host, meilisearch.WithAPIKey(apiKey))
}
func (ml *meili) Insert(data model.Articles) {
	v1, _ := StructToMap(data)
	v := []map[string]interface{}{
		v1,
	}
	_, err := ml.cli.Index("article").AddDocuments(v)
	if err != nil {
		println(err.Error())
		return
	}
}
func (ml *meili) InsertBetch(v []map[string]interface{}) {
	_, err := ml.cli.Index("article").UpdateDocuments(v)
	if err != nil {
		return
	}
}
func (ml *meili) SearchByContent(content string) []interface{} {
	data, err := ml.cli.Index("article").Search(content, &meilisearch.SearchRequest{})
	if err != nil {
		return nil
	}
	if len(data.Hits) > 0 {
		return data.Hits
	}
	return nil
}

func (ml *meili) DelDocById(id string) {
	ts, err := ml.cli.Index("article").DeleteDocument(id)
	if err != nil {
		println(err.Error())
		return
	}
	fmt.Println(ts.Status)
}

func (ml *meili) DelDocByAll() {
	_, err := ml.cli.Index("article").DeleteAllDocuments()
	if err != nil {
		println(err.Error())
		return
	}
}
