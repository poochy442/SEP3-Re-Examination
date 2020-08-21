using DataClasses;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace Server.Adapter
{
    public class RoomAdapter
    {
        public List<Room> rooms;
        public HttpClient Http;

        public RoomAdapter()
        {
            rooms = new List<Room>();
            Http = new HttpClient { BaseAddress = new Uri("http://localhost:8080/") };
        }

        public async Task<List<Room>> GetRooms()
        {
            return rooms;
            /*
            HttpResponseMessage rm = await Http.GetAsync("room");
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<List<Room>>(json);
            */
        }

        public async Task<Room> GetRoom(int id)
        {
            foreach(Room r in rooms)
            {
                if(r.Id == id)
                {
                    return r;
                }
            }
            return null;
            /*
            HttpResponseMessage rm = await Http.GetAsync($"room?id={id}");
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<Room>(json);
            */
        }

        public async Task<bool> AddRoom(Room r)
        {
            rooms.Add(r);
            return true;
            /*
            HttpResponseMessage rm = await Http.PostAsync($"room", new StringContent(JsonConvert.SerializeObject(r)));
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<bool>(json);
            */
        }

        public async Task<bool> EditRoom(int id, Room r)
        {
            for(int i = 0; i < rooms.Count; i++)
            {
                if(rooms[i].Id == id)
                {
                    rooms[i] = r;
                    return true;
                }
            }
            return false;
            /*
            HttpResponseMessage rm = await Http.PutAsync($"room/edit?id={id}", new StringContent(JsonConvert.SerializeObject(r)));
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<bool>(json);
            */
        }

        public async Task<bool> RemoveRoom(int id)
        {
            for (int i = 0; i < rooms.Count; i++)
            {
                if (rooms[i].Id == id)
                {
                    rooms.RemoveAt(i);
                    return true;
                }
            }
            return false;
            /*
            HttpResponseMessage rm = await Http.DeleteAsync($"room/edit?id={id}");
            string json = await rm.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<bool>(json);
            */
        }
    }
}
