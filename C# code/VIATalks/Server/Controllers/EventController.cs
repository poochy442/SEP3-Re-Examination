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
        private EventAdapter Adapter { get; set; }
        public EventController(EventAdapter adapter)
        {
            Adapter = adapter;
        }

        [HttpGet]
        public async Task<List<Event>> GetEvents()
        {
            // TODO: Query Database
            Console.WriteLine("GetEvents called");
            return await Adapter.GetEvents();
        }

        [HttpGet("{id:int}")]
        public async Task<Event> GetEvent(int id)
        {
            // TODO: Query Database
            Console.WriteLine("GetEvent called");
            return await Adapter.GetEvent(id);
        }

        [HttpPost]
        public async Task<bool> AddEvent(
            [FromBody] Event e)
        {
            // TODO: Query Database
            Console.WriteLine("AddEvent called");
            return await Adapter.AddEvent(e);
        }

        [HttpPut]
        public async Task<bool> EditEvent(
            [FromQuery] int id,
            [FromBody] Event e)
        {
            Console.WriteLine("EditEvent called");
            return await Adapter.EditEvent(id, e);
        }

        [HttpDelete]
        public async Task<bool> CancelEvent(
            [FromQuery] int id)
        {
            Console.WriteLine("CancelEvent called");
            return await Adapter.CancelEvent(id);
        }

        /*
        [HttpPost("/request")]
        public async Task<bool> RequestEvent(
            [FromBody] List<string> eventRequest)
        {
            Console.WriteLine("RequestEvent called");
            return await Adapter.RequestEvent(eventRequest);
        }
        */

    }
}
