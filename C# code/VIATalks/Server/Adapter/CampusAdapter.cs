using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using Shared;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace Server.Adapter
{
    public class CampusAdapter
    {
        public List<Campus> campuses;
        public HttpClient Http;

        public CampusAdapter()
        {
            campuses = new List<Campus>();
            Http = new HttpClient { BaseAddress = new Uri("http://localhost:8080/") };
        }

        public async Task<List<Campus>> GetCampuses()
        {
            // TODO: Fix query
            HttpResponseMessage rm = await Http.GetAsync("campus");
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<List<Campus>>(json);
        }

        public async Task<Campus> GetCampus(int id)
        {
            // TODO: Fix query
            HttpResponseMessage rm = await Http.GetAsync($"campus?id={id}");
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Campus>(json);
        }

        public async Task<bool> AddCampus(Campus c)
        {
            // TODO: Fix query
            HttpResponseMessage rm = await Http.PostAsync($"campus", new StringContent(JsonConvert.SerializeObject(c)));
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<bool>(json);
        }

        public async Task<bool> EditCampus(int id, Campus c)
        {
            // TODO: Fix query
            HttpResponseMessage rm = await Http.PutAsync($"campus/edit?id={id}", new StringContent(JsonConvert.SerializeObject(c)));
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<bool>(json);
        }

        public async Task<bool> RemoveCampus(int id)
        {
            // TODO: Fix query
            HttpResponseMessage rm = await Http.DeleteAsync($"campus/edit?id={id}");
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<bool>(json);
        }
    }
}
