package main

import (
	"crypto/hmac"
	"crypto/sha256"
	"crypto/tls"
	"encoding/hex"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"net/url"
	"sort"
	"strings"
	"time"
)

func calculateAuthorizationHeader(requestURL, requestTimeStamp, keyID, keyValue, httpMethod string) string {
	u, err := url.Parse(requestURL)
	if err != nil {
		panic(err)
	}

	var path = u.Path
	m, _ := url.ParseQuery(u.RawQuery)

	var keys []string
	for k := range m {
		keys = append(keys, k)
	}
	sort.Strings(keys)
	var orderedQueries []string
	for _, k := range keys {
		orderedQueries = append(orderedQueries, fmt.Sprintf("%s:%s", k, m[k][0]))
	}

	var queries = strings.Join(orderedQueries, ", ")
	content := fmt.Sprintf("%s\r\n%s\r\n%s\r\n%s", path, queries, requestTimeStamp, httpMethod)
	fmt.Print(content)
	hash := hmac.New(sha256.New, []byte(keyValue))
	hash.Write([]byte(content))
	digest := strings.ToUpper(hex.EncodeToString(hash.Sum(nil)))
	return fmt.Sprintf("AzureCDN %s:%s", keyID, digest)
}

func main() {
	apiURL := "-- your request api url --"
	keyID := "-- your request api key id --"
	keyValue := "-- your request api key value --"
	requestMethod := strings.ToUpper("-- your request method --")
	requestTimestamp := time.Now().UTC().Format(time.RFC3339)

	client := &http.Client{
		Transport: &http.Transport{
			TLSClientConfig: &tls.Config{
				Renegotiation: tls.RenegotiateOnceAsClient,
				Certificates:  nil,
			},
		},
	}

	req, ex := http.NewRequest(requestMethod, apiURL, nil)
	if ex != nil {
		log.Fatal(ex)
	}

	// Add headers
	req.Header.Set("content-type", "application/json")
	req.Header.Set("Authorization", calculateAuthorizationHeader(apiURL, requestTimestamp, keyID, keyValue, "GET"))
	req.Header.Set("x-azurecdn-request-date", requestTimestamp)

	resp, ex := client.Do(req)
	if ex != nil {
		log.Fatal(ex)
	}

	body, _ := ioutil.ReadAll(resp.Body)
	response := string(body)
	fmt.Print(response)
}
