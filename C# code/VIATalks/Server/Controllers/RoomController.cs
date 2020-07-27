using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using DataClasses;

namespace Server.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class RoomController : ControllerBase
    {
        public List<Room> rooms;

        public RoomController()
        {
            rooms = new List<Room>();
        }

        [HttpGet]
        public async Task<List<Room>> GetRooms()
        {
            // TODO: Query Database
            return rooms;
        }

        [HttpGet("{name}")]
        public async Task<Room> GetRoom(String name)
        {
            // TODO: Query Database
            foreach (Room r in rooms)
            {
                if (r.Name == name)
                    return r;
            }

            return null;
        }

        [HttpPost]
        public async Task<ActionResult<Room>> AddRoom(Room room)
        {
            // TODO: Query Database
            rooms.Add(room);

            return CreatedAtAction(nameof(GetRoom), new { name = room.Name }, room);
        }
    }
}
