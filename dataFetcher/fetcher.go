package main

import (
    "fmt"
    "net/http"
    "io/ioutil"
    "os"
    "encoding/json"
)

type searchItem struct{
    AdvertismentId int64 `json:"advertismentId"`
    Title string `json:"title"`
    PropertyStreet string `json:"propertyStreet"`
    PropertyZip string `json:"propertyZip"`
    PropertyCityname string `json:"propertyCityname"`
    PropertyCountry string `json:"propertyCountry"`
    GeoLocation string `json:"geoLocation"`
    ObjectCategory string `json:"objectCategory"`
    SurfaceLiving int64 `json:"surfaceLiving"`
    Currency string `json:"currency"`
    SellingPrice int64 `json:"sellingPrice"`
}

type searchResponse struct{
    ResultCount int64 `json:"resultCount"`
    Start int64 `json:"start"`
    Page int64 `json:"page"`
    PageCount int64 `json:"pageCount"`
    ItemsPerPage int64 `json:"itemsPerPage"`
    HasNextPage bool `json:"hasNextPage"`
    HasPreviousPage bool `json:"hasPreviousPage"`
    Items []searchItem `json:"items"`
}

func main() {

    url := "https://api.tamedia.cloud/homegate/v1/rs/real-estates?lan=de&cht=rentall&cou=CH&nrs=1000"

    req, _ := http.NewRequest("GET", url, nil)

    req.Header.Add("accept", "application/json")
    req.Header.Add("apikey", "6d293d15ec404a00acd74d8a5be27183")

    res, _ := http.DefaultClient.Do(req)

    defer res.Body.Close() //defer closing the body till the end of the function
    body, readErr := ioutil.ReadAll(res.Body)

    if readErr!=nil {
        fmt.Println("An error occurred while reading from the input stream!")
        os.Exit(1)
    }

    var searchResp searchResponse;

    decodeErr := json.Unmarshal(body, &searchResp);
    if (decodeErr!=nil){
        fmt.Println("Error decoding JSON response with error: ", decodeErr.Error());
        os.Exit(1)
    }
    
    fmt.Println("ResultsCount: ",searchResp.ResultCount)
    fmt.Println("ItemsPerPage: ",searchResp.ItemsPerPage)
}