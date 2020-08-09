using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DataClasses;
using Microsoft.AspNetCore.Mvc;
using System.Net;
using System.Net.Http;
using Newtonsoft.Json;

namespace Server.Adapter
{
    public class EventAdapter
    {
        public List<Event> events;
        public HttpClient Http;
        public EventAdapter()
        {
            events = new List<Event>
            {
                // Mock data
                // TODO: Update data
                new Event(
                    1,
                    "How to be cool",
                    "Educational",
                    DateTime.Now,
                    DateTime.Now.AddHours(2),
                    new Event.EventHost("Kenneth", "Jensen", "123@abc.com", "12345678"),
                    new Room(1, 301, 'E', 100, 60),
                    new Campus(1, "Horsens", 8700, "CampusAdress"))
            };
            events[0].NumberOfSeats = 50;
            Http = new HttpClient { BaseAddress = new Uri("http://localhost:8080/") };
        }

        public async Task<List<Event>> GetEvents()
        {
            HttpResponseMessage rm = await Http.GetAsync("event/upcoming");
            String json = await rm.Content.ReadAsStringAsync();
            return events = JsonConvert.DeserializeObject<List<Event>>(json);
        }

        public async Task<Event> GetEvent(int id)
        {
            await GetEvents();
            foreach (Event e in events)
            {
                if (e.Id == id)
                    return e;
            }

            return null;
        }

        public async Task<ActionResult<bool>> AddEvent(Event e)
        {
            HttpResponseMessage rm = await Http.PostAsync("create", new StringContent(JsonConvert.SerializeObject(e)));
            String json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<bool>(json);
        }

        public async Task<ActionResult<bool>> EditEvent(int id, Event e)
        {
            HttpResponseMessage rm = await Http.PutAsync("update", new StringContent(JsonConvert.SerializeObject(e)));
            String json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<bool>(json);
        }

        public async Task<ActionResult<bool>> CancelEvent(int id)
        {
            HttpResponseMessage rm = await Http.DeleteAsync($"delete?id={id}");
            String json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<bool>(json);
        }
    }
}
