package my_tools

import (
	"errors"
	"reflect"
)

// StructToMap 将结构体转换为map
func StructToMap(obj interface{}) (map[string]interface{}, error) {
	objValue := reflect.ValueOf(obj)
	objType := objValue.Type()

	// 确保传入的参数是一个结构体
	if objType.Kind() != reflect.Struct {
		return nil, errors.New("invalid")
	}

	resultMap := make(map[string]interface{})

	for i := 0; i < objValue.NumField(); i++ {
		field := objValue.Field(i)
		fieldType := objType.Field(i)
		// 获取字段名
		fieldName := fieldType.Name
		// 获取字段值
		fieldValue := field.Interface()
		resultMap[fieldName] = fieldValue
	}
	return resultMap, nil
}
