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
        private EventAdapter adapter;
        public EventController()
        {
            adapter = new EventAdapter();
        }

        [HttpGet]
        public async Task<List<Event>> GetEvents()
        {
            // TODO: Query Database
            Console.WriteLine("GetEvents called");
            return await adapter.GetEvents();
        }

        [HttpGet("{eventName}")]
        public async Task<Event> GetEvent(
            [FromQuery] String eventName)
        {
            // TODO: Query Database
            Console.WriteLine("GetEvent called");
            return await adapter.GetEvent(eventName);
        }

        [HttpPost]
        public async Task<ActionResult<bool>> AddEvent(
            [FromBody] Event e)
        {
            // TODO: Query Database
            Console.WriteLine("AddEvent called");
            return await adapter.AddEvent(e);
        }

        [HttpPut]
        public async Task<ActionResult<bool>> EditEvent(
            [FromQuery] int id,
            [FromBody] Event e)
        {
            Console.WriteLine("EditEvent called");
            return await adapter.EditEvent(id, e);
        }

        [HttpDelete]
        public async Task<ActionResult<bool>> CancelEvent(
            [FromQuery] int id)
        {
            Console.WriteLine("CancelEvent called");
            return await adapter.CancelEvent(id);
        }

    }
}
