using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DataClasses;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Server.Adapter;
using Shared;

namespace Server.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CampusController : ControllerBase
    {
        private CampusAdapter Adapter { get; set; }
        public CampusController()
        {
            Adapter = new CampusAdapter();
        }

        [HttpGet]
        public async Task<List<Campus>> GetCampuses()
        {
            Console.WriteLine("GetCampuses called");
            return await Adapter.GetCampuses();
        }

        [HttpGet]
        public async Task<Campus> GetCampus(
            [FromQuery] int id)
        {
            Console.WriteLine("GetCampus called");
            return await Adapter.GetCampus(id);
        }

        [HttpPost]
        public async Task<bool> AddCampus(
            [FromBody] Campus c)
        {
            Console.WriteLine("AddCampus called");
            return await Adapter.AddCampus(c);
        }

        [HttpPut]
        public async Task<bool> EditCampus(
            [FromQuery] int id,
            [FromBody] Campus c)
        {
            Console.WriteLine("EditCampus called");
            return await Adapter.EditCampus(id, c);
        }

        [HttpDelete]
        public async Task<bool> RemoveCampus(
            [FromQuery] int id)
        {
            Console.WriteLine("RemoveCampus called");
            return await Adapter.RemoveCampus(id);
        }

    }
}
