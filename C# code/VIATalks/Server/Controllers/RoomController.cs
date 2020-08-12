using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Server.Adapter;
using DataClasses;

namespace Server.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RoomController : ControllerBase
    {
        private RoomAdapter Adapter { get; set; }
        public RoomController()
        {
            Adapter = new RoomAdapter();
        }

        [HttpGet]
        public async Task<List<Room>> GetRooms()
        {
            Console.WriteLine("GetRooms called");
            return await Adapter.GetRooms();
        }

        [HttpGet]
        public async Task<Room> GetRoom(
            [FromQuery] int id)
        {
            Console.WriteLine("GetRoom called");
            return await Adapter.GetRoom(id);
        }

        [HttpPost]
        public async Task<bool> AddRoom(
            [FromBody] Room r)
        {
            Console.WriteLine("AddRoom called");
            return await Adapter.AddRoom(r);
        }

        [HttpPut]
        public async Task<bool> EditCampus(
            [FromQuery] int id,
            [FromBody] Room r)
        {
            Console.WriteLine("EditRoom called");
            return await Adapter.EditRoom(id, r);
        }

        [HttpDelete]
        public async Task<bool> RemoveCampus(
            [FromQuery] int id)
        {
            Console.WriteLine("RemoveRoom called");
            return await Adapter.RemoveRoom(id);
        }
    }
}
