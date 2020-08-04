using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using DataClasses;
using System.Net.NetworkInformation;
using Server.Adapter;

namespace Server.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class EventController : ControllerBase
    {
<<<<<<< HEAD
        private EventAdapter adapter;
        public EventController()
        {
            adapter = new EventAdapter();
=======
        private EventAdapter Adapter { get; set; }
        public EventController(EventAdapter adapter)
        {
            Adapter = adapter;
>>>>>>> C#
        }

        [HttpGet]
        public async Task<List<Event>> GetEvents()
        {
            // TODO: Query Database
            Console.WriteLine("GetEvents called");
<<<<<<< HEAD
            return await adapter.GetEvents();
        }

        [HttpGet("{eventName}")]
        public async Task<Event> GetEvent(
            [FromQuery] String eventName)
        {
            // TODO: Query Database
            Console.WriteLine("GetEvent called");
            return await adapter.GetEvent(eventName);
=======
            return await Adapter.GetEvents();
        }

        [HttpGet("{id:int}")]
        public async Task<Event> GetEvent(int id)
        {
            // TODO: Query Database
            Console.WriteLine("GetEvent called");
            return await Adapter.GetEvent(id);
>>>>>>> C#
        }

        [HttpPost]
        public async Task<ActionResult<bool>> AddEvent(
            [FromBody] Event e)
        {
            // TODO: Query Database
            Console.WriteLine("AddEvent called");
<<<<<<< HEAD
            return await adapter.AddEvent(e);
=======
            return await Adapter.AddEvent(e);
>>>>>>> C#
        }

        [HttpPut]
        public async Task<ActionResult<bool>> EditEvent(
            [FromQuery] int id,
            [FromBody] Event e)
        {
            Console.WriteLine("EditEvent called");
<<<<<<< HEAD
            return await adapter.EditEvent(id, e);
=======
            return await Adapter.EditEvent(id, e);
>>>>>>> C#
        }

        [HttpDelete]
        public async Task<ActionResult<bool>> CancelEvent(
            [FromQuery] int id)
        {
            Console.WriteLine("CancelEvent called");
<<<<<<< HEAD
            return await adapter.CancelEvent(id);
=======
            return await Adapter.CancelEvent(id);
>>>>>>> C#
        }

    }
}
